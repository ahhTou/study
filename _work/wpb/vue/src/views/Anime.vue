<template>
    <div id="animeWrapper">
        <h1>动画列表</h1>
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
                        <button id="edit" @click="editThisColumn(item.id)">编辑</button>
                        <button id="del" @click="delThisColumn(item.id)">删除</button>
                    </td>
                </tr>
                </tbody>
            </table>
            <div id="loading" v-if="onePageData===null">
                <loading-animation ref="wrapper" :start-loading="true"/>
            </div>
        </div>


        <div id="addAnimeWrapper">
            sss
        </div>

        <div id="pageBtnWrapper">
            <button @click="nextPage(false)">上一页</button>
            <div>{{$route.params.pathMatch + "/" + pages}}</div>
            <button @click="nextPage(true)">下一页</button>
        </div>
    </div>
</template>

<script>
    import {getHowMuchPages, getOnePagesValue, delOneAnimeById} from '../network/anime.js'
    import LoadingAnimation from "../components/LoadingAnimation";

    export default {
        name: "Anime",
        components: {
            LoadingAnimation
        },
        data() {
            return {
                currentShowPage: 1,
                currentSelect: null,

                pages: 0,
                onePageData: null,

            }
        }
        ,
        created() {
            (async () => {
                console.log("> 准备请求页数")
                this.pages = await this._getHowMuchPagesService() / 20
                this.pages = Math.ceil(this.pages)
                console.log(this.pages)
                if (this.pages >= 1) {
                    console.log("> 设置了页数")
                    this.currentShowPage = this.$route.params.pathMatch
                    this.onePageData = await this._getOnePageValueService()
                    console.log("> 得到了数据")
                }
            })()
        }
        ,
        mounted() {

        },
        methods: {
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
            editThisColumn(id) {
                console.log("编辑:", id)
            }
            ,
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
            _getHowMuchPagesService() {
                return new Promise((resolve, reject) => {
                    getHowMuchPages().then(res => {
                        console.log("> 得到了页数", res.data)
                        resolve(res.data)
                    }, err => {
                        console.log("> 得到页数失败了")
                        reject(0)
                    })
                })
            }
            ,
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
                let data = new Date(value)
                return data.getFullYear() + '-' + data.getMonth() + '-' + data.getDay()
            }
        }
        ,
    }
</script>

<style lang="scss" scoped>
    * {
        margin: 0;
        padding: 0
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
        h1 {
            color: white;
        }

        position: relative;
        z-index: 100;
        $animeTableWidth: 1000;

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

        #addAnimeWrapper {
            float: right;
            box-sizing: border-box;
            width: 1000px;
            border: 3px solid black;
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