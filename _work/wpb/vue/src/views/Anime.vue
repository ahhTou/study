<template>
    <div id="AnimeWrapper">
        <div id="tableWrapper">
            <table>
                <thead>
                <tr>
                    <th>番剧名称</th>
                    <th>番剧类型</th>
                    <th>完结时间</th>
                </tr>
                </thead>
                <!--                <tbody id="tableBody">
                                <tr  :data-movie-id>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                </tbody>-->
            </table>
        </div>
    </div>
</template>

<script>
    import {getHowMuchPages} from '../network/anime.js'

    export default {
        name: "Anime",
        data() {
            return {
                currentShowPage: 1,
                currentSelect: null,


                pages: 0,

            }
        },
        mounted() {
            console.log("> 准备请求页数")
            this.pages = this._getHowMuchPagesService()
            console.log("> 设置了页数")
        },
        methods: {
            _getHowMuchPagesService() {
                getHowMuchPages().then(res => {
                    console.log("> 得到了页数", res.data)
                    return res.data
                }, err => {
                    console.log("> 得到页数失败了")
                    return 0
                })
            },
        }
    }
</script>

<style scoped>

</style>