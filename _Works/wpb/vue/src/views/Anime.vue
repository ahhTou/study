<template>
    <div id="animeWrapper">
        <div id="title">动画列表</div>
        <div id="tableWrapper">
            <table>
                <thead>
                <tr>
                    <th style="width: 300px;">番剧名称</th>
                    <th style="width: 300px;">番剧类型</th>
                    <th style="width: 300px;">完结时间</th>
                    <th style="width: 100px;">操作</th>
                </tr>
                </thead>
                <tbody id="tableBody" v-if="onePageData!==null">
                <tr v-for="item in onePageData" :data-anime-id="item.id">
                    <td style="width: 300px;">{{item.title}}</td>
                    <td style="width: 300px;">{{item.types | typesFilter}}</td>
                    <td style="width: 300px;">{{item.endTime | timeFilter}}</td>
                    <td style="width: 100px;">
                        <button id="edit" @click="editThisColumn(item)">编辑</button>
                        <button id="del" @click="delThisColumn(item.id)">删除</button>
                    </td>
                </tr>
                </tbody>
            </table>
            <div id="loading" v-if="onePageData===null">
                <loading-animation ref="wrapper" :start-loading="true"/>
            </div>
        </div>


        <div id="editAnimePanelWrapper">
            <h2 style="margin: 20px">{{panelTitle}}</h2>
            <label>标题<br/>
                <input type="text" v-model="currentAnimeData.title">
            </label>
            <label>封面地址<br/>
                <input type="text" v-model="currentAnimeData.imgUrl" placeholder="可以为空">
            </label>
            <label>完结时间<br/>
                <input type="date" v-model="endTimeForInput">
            </label>
            <div>
                类型<br/>
                <div id="typeWrapper" ref="typesWrapper" @click="typesController($event)">
                    <div class="typeBlock" v-for="item in allTypes" :data-types="item">
                        {{item}}
                    </div>
                </div>
            </div>
            操作<br/>
            <div id="buttonWrapper">
                <button id="btnEnter" :value="panelTitle" @click.prevent="panelEnter">提交</button>
                <button id="btnClear" @click.prevent="clearInput">重置</button>
            </div>
        </div>

        <div id="pageBtnWrapper">
            <button @click="nextPage(false)">上一页</button>
            <div>{{$route.params.pathMatch + "/" + pages}}</div>
            <button @click="nextPage(true)">下一页</button>
        </div>
    </div>
</template>

<script>
    import {
        addOneAnime,
        delOneAnimeById,
        getAllTypes,
        getHowMuchPages,
        getOnePagesValue,
        updateOne
    } from '../network/anime.js'
    import LoadingAnimation from "../components/LoadingAnimation";
    import Anime from "../assets/js/Anime";

    export default {
        name: "Anime",
        components: {
            LoadingAnimation
        },
        data() {
            return {
                currentShowPage: 1,
                currentSelect: null,
                currentTypes: [],

                pages: 0,
                onePageData: null,
                allTypes: null,

                panelTitle: '添加',

                /* 表单绑定的 */
                currentAnimeData: new Anime(),
                /* 编辑时存储 */
                editData: new Anime()
            }
        }
        ,
        created() {
            (async () => {
                console.log("> 准备请求页数")
                this.pages = Math.ceil(await this._getHowMuchPagesService() / 20)
                if (this.pages >= 1) {
                    console.log("> 设置了页数")
                    this.currentShowPage = this.$route.params.pathMatch
                    this.onePageData = await this._getOnePageValueService()
                    console.log("> 得到了数据")
                }
            })()
        }
        ,
        computed: {
            endTimeForInput: {
                get() {
                    return this.currentAnimeData.getEndTimeForInput
                },
                set(val) {
                    console.log(val)
                    this.currentAnimeData.setEndTimeForInput = val
                }
            }
        },
        mounted() {
            (async () => {
                console.log("> 准备请求类型")
                this.allTypes = await this._getAllTypes()
                console.log("> 已获取类型")
            })()
        },
        methods: {
            /* 判断type选择还是不选择时的样式 */
            typesClass(obj) {
                if (obj === 'typeBlock') {
                    return 'typeBlock typeBlockChoose'
                } else {
                    return 'typeBlock'
                }
            }
            ,
            /* 处理点击时选中和不选中 */
            typesController(e) {
                e.target.className = this.typesClass(e.target.className)
                if (e.target.dataset.types) {
                    if (!this.currentTypes.includes(e.target.dataset.types)) {
                        this.currentTypes.push(e.target.dataset.types)
                    } else {
                        let index = this.currentTypes.indexOf(e.target.dataset.types)
                        this.currentTypes.splice(index, 1)
                    }
                }
            }
            ,
            /* 控制上下页 */
            nextPage(isNext) {
                (async () => {
                    let urlId = parseInt(this.$route.params.pathMatch)
                    if (isNext && urlId < this.pages) {
                        this.onePageData = null
                        console.log('> 下一页')
                        await this.$router.replace('/anime/' + (urlId + 1))
                        this.onePageData = await this._getOnePageValueService()

                    }
                    if (!isNext && urlId > 1) {
                        this.onePageData = null
                        console.log('> 上一页')
                        await this.$router.replace('/anime/' + (urlId - 1))
                        this.onePageData = await this._getOnePageValueService()
                    }
                })()
            }
            ,
            /* 编辑该列 */
            editThisColumn(obj) {
                this.panelTitle = '编辑'
                this.editData.setEndTime = this.currentAnimeData.setEndTime = obj.endTime
                this.editData.setTitle = this.currentAnimeData.setTitle = obj.title
                this.editData.setImgUrl = this.currentAnimeData.setImgUrl = obj.imgUrl === null ? null : obj.imgUrl
                this.editData.setTypes = this.currentAnimeData.setTypes = obj.types
                this.editData.setId = this.currentAnimeData.setId = obj.id

                this.currentTypes = this.currentAnimeData.types

                // 样式切换
                let $typesWrapper = this.$refs.typesWrapper;
                for (let i = 0; i < $typesWrapper.children.length; i++) {
                    if (this.currentAnimeData.types.includes($typesWrapper.children[i].dataset.types)) {
                        $typesWrapper.children[i].className = 'typeBlock typeBlockChoose'
                    } else {
                        $typesWrapper.children[i].className = 'typeBlock';
                    }
                }
            }
            ,
            /* 删除该列 */
            delThisColumn(id) {
                (async () => {
                    console.log("> 删除:", id)
                    let res = await this._delOnePageService(id)
                    this.onePageData = null
                    if (res) {
                        this.onePageData = await this._getOnePageValueService()
                        this.pages = await this._getHowMuchPagesService() / 20
                        this.pages = Math.ceil(this.pages)
                    }
                })()
            }
            ,
            /* 添加面板添加 或者 编辑 */
            panelEnter() {
                let obj = this.currentAnimeData
                let key = this.panelTitle

                console.log("> 准备提交", obj)
                if (key === '添加' && obj.title !== null && obj.title !== '' && obj.title !== undefined) {
                    console.log("> 准备添加动画")
                    this._addOneAnimeService(obj).then(res => {
                        console.log('> 添加动画成功', res)
                        this.clearInput()
                        this.refresh(true)
                    }, err => {
                        console.log(err)
                    })
                } else if (key === '编辑' && obj.id !== null && obj.id !== '' && obj.id !== undefined) {
                    this._editOneAnimeService(obj).then(res => {
                        console.log("> 编辑成功", res)
                        this.clearInput()
                        this.refresh(false)
                    }, err => {
                        console.log("> 编辑失败", err)
                    })
                } else {
                    console.log("> 本地拒绝了添加操作")
                }


            }
            ,
            /* 清空按钮 */
            clearInput() {
                this.panelTitle = '添加'

                this.currentAnimeData = new Anime()
                this.editData = new Anime()

                /* 清除选中状态 */
                let $typesWrapper = this.$refs.typesWrapper;
                for (let i = 0; i < $typesWrapper.children.length; i++) {
                    $typesWrapper.children[i].className = 'typeBlock';
                }
            }
            ,
            /* 刷新操作 */
            refresh(isGoLastPages) {
                (async () => {
                    if (isGoLastPages) await this.$router.replace('/anime/' + this.pages)
                    this.onePageData = await this._getOnePageValueService()
                    this.pages = await this._getHowMuchPagesService() / 20
                    this.pages = Math.ceil(this.pages)
                })()
            }
            ,

            /* 获取多少页 */
            _getHowMuchPagesService() {
                return new Promise((resolve, reject) => {
                    getHowMuchPages().then(res => {
                        console.log("> 得到了页数")
                        resolve(res.data)
                    }, err => {
                        console.log("> 得到页数失败了")
                        reject(0)
                    })
                })
            }
            ,
            /* 添加一个 */
            _addOneAnimeService(obj) {
                return new Promise(((resolve, reject) => {
                    addOneAnime(obj).then(res => {
                        resolve(res.data)
                    }, err => {
                        reject(false)
                    })
                }))
            }
            ,
            /* 编辑 */
            _editOneAnimeService(obj) {
                return new Promise(((resolve, reject) => {
                    updateOne(obj).then(res => {
                        resolve(res.data)
                    }, err => {
                        reject(false)
                    })
                }))
            },

            /* 获取单页内容 */
            _getOnePageValueService() {
                return new Promise((resolve, reject) => {
                    getOnePagesValue(this.$route.path).then(res => {
                        console.log("> 得到了一页的数据")
                        resolve(JSON.parse(res.data))
                    }, err => {
                        console.log("> 得到数据失败", err)
                        reject(false)
                    })
                })
            }
            ,
            /* 删除一列 */
            _delOnePageService(id) {
                return new Promise((resolve, reject) => {
                    delOneAnimeById(id).then(res => {
                        if (res.data) {
                            resolve(true)
                        } else {
                            console.log("> 删除出错")
                            resolve(false)
                        }
                    }, err => {
                        console.log("> 删除请求服务器异常")
                        reject(false)
                    })
                })
            }
            ,
            /* 得到全部的类型 */
            _getAllTypes() {
                return new Promise((resolve, reject) => {
                    getAllTypes().then(res => {
                        resolve(res.data)
                    }, err => {
                        reject(err)
                    })
                })
            }


        }
        ,
        filters: {
            typesFilter: function (value) {
                if (value.length === 0) {
                    return "动画"
                } else if (value.length === 1) {
                    return value[0]
                } else if (value.length === 2) {
                    return value[0] + ',' + value[1]
                } else if (value.length >= 3) {
                    return value[0] + ', ' + value[1] + ' 等'
                }
            }
            ,
            timeFilter: function (value) {
                return new Anime().timeFilter(value)
            }
        }
        ,
        watch: {
            currentTypes(value) {
                this.currentAnimeData.types = value
            }
        }
    }
</script>

<style lang="scss" scoped>
    * {
        margin: 0;
        padding: 0;
        outline: none;
    }

    input {
        background: none;
        border: none;
    }

    li {
        list-style: none
    }

    img {
        vertical-align: top;
        border: none
    }

    button {
        outline: none;
        border: none;
        cursor: pointer;
    }

    @mixin flex() {
        display: flex;
        justify-content: center;
        align-items: center;
    }


    #animeWrapper {
        #title {
            text-align: start;
            margin: 10px;
            width: 100%;
            font-size: 35px;
        }

        position: relative;
        z-index: 100;
        width: 1339px;
        $animeTableWidth: 1000;
        display: flex;
        flex-wrap: wrap;

        #tableWrapper {
            $tableHeaderHeight: 40;
            $tableBodyTrHeight: 35;

            backdrop-filter: blur(15px);
            position: relative;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 10px 10px 30px rgba(100, 100, 100, 0.5);

            width: $animeTableWidth + px;
            height: ($tableHeaderHeight + ($tableBodyTrHeight * 20)) + px;

            table {
                position: relative;
                width: 100%;
                overflow: hidden;
                border-spacing: 0;
                border: none;

                thead th {
                    height: $tableHeaderHeight + px;
                    background-color: rgba(255, 255, 255, 0.2);
                }

                tbody {
                    tr {
                        &:nth-child(2n + 1) {
                            color: black;
                            background-color: rgba(255, 255, 255, 0.3);
                            height: $tableBodyTrHeight + px;
                            text-align: center;
                            transition: all 1s;
                        }

                        &:nth-child(2n) {
                            background-color: rgba(255, 255, 255, 0.2);
                            color: black;
                            height: $tableBodyTrHeight + px;
                            transition: all 1s;
                            text-align: center;
                        }

                        &:hover {
                            background-color: rgba(255, 255, 255, 1);
                            box-shadow: 10px 10px 30px rgba(100, 100, 100, 0.5);
                        }

                        button {
                            width: 50%;
                            height: $tableBodyTrHeight + px;
                            background: none;
                        }

                        #edit {
                            &:hover {
                                color: white;
                                background: rgb(82, 170, 217);
                            }
                        }

                        #del {
                            &:hover {
                                color: white;
                                background: rgb(196, 60, 60);

                            }
                        }
                    }
                }
            }

            #loading {
                @include flex();
                width: 100%;
                height: 100%;
                font-size: 50px;
                line-height: 50px;
                color: white;
            }

        }

        #editAnimePanelWrapper {
            float: right;
            box-sizing: border-box;
            display: flex;
            align-items: center;
            flex-direction: column;
            width: 300px;
            margin: 0 0 0 30px;
            border-radius: 10px;
            backdrop-filter: blur(15px);
            box-shadow: 10px 10px 30px rgba(100, 100, 100, 0.5);

            #typeWrapper {
                @include flex();
                flex-wrap: wrap;
                width: 200px;
                margin: 20px;

                .typeBlock {
                    cursor: pointer;
                    @include flex();
                    width: 70px;
                    margin: 5px;
                    box-sizing: border-box;
                    flex-wrap: wrap;
                    border: 1px solid rgb(39, 86, 130);
                    transition: all .3s;

                    &:hover {
                        border: 1px solid rgba(39, 86, 130, 0);
                        background-color: rgb(3, 110, 191);
                        color: white;
                    }
                }

                .typeBlockChoose {
                    border: 1px solid rgba(39, 86, 130, 0);
                    background-color: rgb(3, 110, 191);
                    color: white;
                }

            }

            label {
                margin-bottom: 30px;

                input[type="text"], input[type="date"] {
                    margin-top: 10px;
                    font-size: 15px;
                    width: 200px;
                    border-bottom: 1px rgb(39, 86, 130) solid;
                    height: 30px;
                }
            }

            #buttonWrapper {
                display: flex;
                width: 200px;

                #btnEnter {
                    cursor: pointer;
                    margin-top: 10px;
                    height: 30px;
                    min-width: 140px;
                    box-sizing: border-box;
                    background: rgba(85, 126, 161, 0);
                    border: 1px solid rgba(39, 86, 130, 1);
                    transition: all .4s;

                    &:hover {
                        border: 1px solid rgba(39, 86, 130, 0);
                        background: rgb(88, 143, 191);
                        color: white;
                    }
                }

                #btnClear {
                    margin-top: 10px;
                    height: 30px;
                    width: 50px;
                    margin-left: 1px;
                    background-color: #f86262;
                    color: white;
                    transition: all .4s;

                    &:hover {
                        background-color: #ff2323;
                    }
                }
            }


        }

        #pageBtnWrapper {
            @include flex();
            height: 50px;
            box-sizing: border-box;
            margin: 30px 0 30px 0;
            width: $animeTableWidth + px;

            div {
                @include flex();
                width: 100px;
                height: 50px;
            }

            button {
                transition: all .5s;
                backdrop-filter: blur(15px);
                background: none;
                border: 5px;
                width: 150px;
                height: 50px;

                &:active {
                    transform: scale(1.05);
                }
            }
        }
    }

    .fade-enter-active, .fade-leave-active {
        transition: opacity .5s;
    }

    .fade-enter, .fade-leave-to {
        opacity: 0;
    }
</style>