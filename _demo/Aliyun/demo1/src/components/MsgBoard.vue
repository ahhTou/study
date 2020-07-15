<template>
    <div>
        <div id="msgBoardWrapper">

            <transition name="fade">
                <div id="shade" v-show="isOpen"></div>
            </transition>

            <div id="btn" @click="change(true)">留言板</div>

            <transition name="bigger">
                <div id="wrapper" v-show="isOpen&&!isSending">
                    <div v-for="item in currentMsgData" class="msgBlock">
                        <div class="sends">
                            <div class="sendName">{{item.sendName}}</div>
                            <div class="sendTime">{{item.sendTime | timeFilter}}</div>
                        </div>
                        <div class="content">{{item.content}}</div>
                    </div>
                </div>
            </transition>

            <transition name="fade">
                <div id="btnForSendWrapper" :class="btnForSendWrapperClassName"
                     v-show="isOpen" @touchmove.prevent>
                    <div id="btnForSend" @click="isSending?toSend():toSending()">{{this.btnForSendText}}</div>
                    <transition name="fade">
                        <div id="formWrapper" v-if="isSending">
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
                    <div id="exit" @click="change(false)">轻点退出</div>
                </div>
            </transition>
        </div>
    </div>
</template>

<script>
    import {getAllMsgBoardData, setOneMsgBoardData} from 'src/network/api-msg-board'
    import MsgBoard from '../assets/pojo/MsgBoard'
    import MyFilter from '../assets/utils/MyFilter'

    export default {
        name: 'MsgBoard',
        data() {
            return {
                isOpen: false,
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
                        this.isOpen = false
                        this.isSending = true
                        this.closeSend()
                    }
                } else {
                    // 打开
                    this.isOpen = true
                    this.isSending = false
                }
            },
            toSend() {
                // 发送中，不能重复点击
                if (!this.sendingLock) {
                    let msg = new MsgBoard(this.currentSendName, this.currentContent, new Date())
                    if (msg.content !== '' && msg.content !== null) {
                        this.btnForSendText = '发送中...'
                        // 内容不为空，则发送
                        setOneMsgBoardData(msg)
                            .then(res => {
                                if (res.data) {
                                    this.refresh()
                                    this.closeSend()
                                    this.isSending = false
                                    this.sendingLock = false
                                }
                            })
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
                this.isOpen = true
                this.isSending = true
            },
            refresh() {
                this.currentMsgData = []
                getAllMsgBoardData().then(res => {
                    for (let j = 0, len = res.data.length; j < len; j++) {
                        this.currentMsgData.push(JSON.parse(res.data[j]))
                    }
                })
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
        }
    }
</script>

<style lang="scss" scoped>
    @import 'src/assets/css/style';
    @import "src/assets/css/base";

    #msgBoardWrapper {
        position: absolute;
        width: 100vw;
        top: 0;
        right: 0;
        display: flex;
        justify-content: center;

        #btn {
            cursor: pointer;
            position: fixed;
            z-index: 98;
            @include flex;
            right: 110px;
            transform: translate(50%, -50%);

            top: 30px;
            height: 50px;
            width: 100px;
            @include blurWindow;

        }

        #shade {
            @include shade;
            z-index: 101;
        }

        .sendOpen {
            height: 80vh;
        }

        .sendClose {
            height: 120px;
        }

        #btnForSendWrapper {
            $width: 300px;
            $br: 15px;
            position: fixed;
            width: $width;
            display: flex;
            flex-direction: column;
            align-items: center;

            bottom: 0;

            z-index: 201;
            padding: 0;
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
                    height: 300px;
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
                @include flex;
                position: absolute;
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

        #wrapper {
            position: relative;
            $scrollbarWidth: 1px;
            width: 310px;
            padding-left: $scrollbarWidth;
            box-sizing: border-box;

            z-index: 200;

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
    }


</style>