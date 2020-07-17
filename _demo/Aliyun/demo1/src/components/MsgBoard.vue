<template>
    <shade-slots>
        <template #content>
            <transition name="bigger">
                <div id="wrapperForMsgBoard" v-show="!isSending">
                    <div v-for="item in currentMsgData" class="msgBlock" @click.stop>
                        <div class="sends">
                            <div class="sendName">{{item.sendName}}</div>
                            <div class="sendTime">{{item.sendTime | timeFilter}}</div>
                        </div>
                        <div class="content">{{item.content}}</div>
                    </div>
                </div>
            </transition>
        </template>

        <template #controller>
            <div id="btnForSendWrapper" :class="btnForSendWrapperClassName">
                <div id="btnForSend" @click.stop="isSending?toSend():toSending()">{{btnForSendText}}</div>
                <transition name="fade">
                    <div id="formWrapper" v-if="isSending" @click.stop>
                        输入您的大名
                        <label>
                            <input id="nameArea" type="text" v-model="currentSendName">
                        </label>
                        留言
                        <label>
                            <textarea id="textArea" v-model="currentContent"/>
                        </label>
                    </div>
                </transition>
                <div id="exit" @click.stop="change(false)">轻点退出</div>
            </div>
        </template>
    </shade-slots>
</template>

<script>
    import MsgBoard from '../assets/pojo/MsgBoard'
    import MyFilter from '../assets/utils/MyFilter'
    import ShadeSlots from './ShadeSlots'

    export default {
        name: 'MsgBoard',
        components: {ShadeSlots},
        data() {
            return {
                isSending: false,

                currentMsgData: [],
                currentSendName: null,
                currentContent: null,

                sendingLock: false, //负责锁定发送

                btnForSendWrapperClassName: 'sendClose',
                btnForSendText: '点击此处开始留言'

            }
        },
        mounted() {
            this.refresh()
        },
        methods: {
            change(value) {
                if (!value) {
                    // 退出
                    if (this.isSending) {
                        this.isSending = false
                        this.closeSend()
                    } else {
                        this.$router.go(-1)
                    }
                } else {
                    // 打开
                    this.isSending = false
                }
            },
            async toSend() {
                // 发送中，不能重复点击
                if (!this.sendingLock) {
                    let msg = new MsgBoard(this.currentSendName, this.currentContent, new Date())
                    if (msg.content !== '' && msg.content !== null) {
                        this.btnForSendText = '发送中...'

                        // 内容不为空，则发送
                        if (await msg.updateSelf()) {
                            await this.refresh()
                            this.closeSend()
                            this.isSending = false
                            this.sendingLock = false

                        }
                    } else {
                        // 如果输入内容为空，则退到留言区
                        this.closeSend()
                        this.isSending = false
                    }
                } else {
                    this.btnForSendText = '发送中，请不要重复点击'
                }

            },
            toSending() {
                this.btnForSendWrapperClassName = 'sendOpen'
                this.btnForSendText = '再次点击进行留言'
                this.isSending = true
            },

            async refresh() {
                this.currentMsgData = await MsgBoard.allList()
            },

            closeSend() {
                this.$nextTick(() => {
                    this.btnForSendWrapperClassName = 'sendClose'
                    this.btnForSendText = '点击此处开始留言'
                })
            }
        },
        filters: {
            timeFilter(date) {
                return MyFilter.timeFilter(date)
            }
        },
    }
</script>

<style lang="scss" scoped>
    @import 'src/assets/css/style';
    @import "src/assets/css/base";


    .sendOpen {
        height: 70vh;
    }

    .sendClose {
        height: 120px;
    }

    #btnForSendWrapper {
        position: fixed;

        $width: 300px;
        bottom: 0;

        $br: 15px;
        width: $width;

        display: flex;
        flex-direction: column;
        align-items: center;

        backdrop-filter: blur(3px);
        border-radius: $br $br 0 0;
        transition: all .3s;


        #formWrapper {
            display: flex;
            flex-direction: column;
            align-items: center;

            margin-top: 100px;
            color: rgb(234, 73, 95);

            input {
                width: 300px;
                height: 50px;
                font-size: 20px;
                border: 1px solid rgb(234, 73, 95);
                border-radius: 15px;
                padding: 5px;
                box-sizing: border-box;
                margin: 5px 0 5px 0;
            }

            #textArea {
                height: 200px;
            }

            textarea {
                resize: none;
                width: 300px;
                border: 1px solid rgb(234, 73, 95);
                outline: none;
                border-radius: 15px;
                padding: 15px;
                box-sizing: border-box;
                font-size: 20px;

            }

            #nameArea {
                width: 300px;
                resize: none;
            }

        }

        #exit {
            @include flex;
            position: fixed;
            bottom: 0;
            height: 50px;
            color: rgb(234, 73, 95);
            cursor: pointer;
        }

        #btnForSend {
            position: absolute;
            @include flex;
            color: white;
            background: rgb(234, 73, 95);
            width: $width;
            border-radius: $br;
            height: 55px;
            box-shadow: $shadow;
            cursor: pointer;
            font-size: 20px;
            transition: all .5s;
            font-weight: bolder;
        }

    }

    #wrapperForMsgBoard {
        position: relative;

        $scrollbarWidth: 1px;
        width: 300px;
        padding-left: $scrollbarWidth;
        box-sizing: border-box;
        display: flex;
        align-items: center;
        flex-direction: column;

        .msgBlock {
            flex-shrink: 0;
            background-color: rgb(237, 237, 238);
            border-radius: 25px;
            width: 300px;
            overflow: hidden;
            margin: 5px 0 5px 0;

            transition: all .5s;


            .sends {
                height: 55px;
                width: 300px;
                display: flex;
                justify-content: center;
                flex-direction: column;
                align-items: flex-start;

                background: white;
                padding-left: 30px;
                box-sizing: border-box;

                .sendName {
                    height: 20px;
                    font-weight: bolder;
                    color: rgb(234, 73, 95);
                }

                .sendTime {
                    font-size: 10px;
                    height: 20px;
                }

            }

            .content {
                padding: 10px 30px 10px 30px;
                display: flex;

                text-align: left;
                align-items: center;
                justify-content: flex-start;
                min-height: 65px;
                background-color: rgb(237, 237, 238);
            }

        }


    }


</style>