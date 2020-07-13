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
                <div id="btnForSendWrapper" class="sendClose" ref="btnForSendWrapperRef" v-show="isOpen">
                    <div id="btnForSend" @click="isSending?toSend():toSending()">欢迎留言~</div>
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
                sending: false,
            }
        },
        mounted() {
            let a = new MsgBoard('ahhTou', 'hello', new Date())
            getAllMsgBoardData().then(res => {
                for (let j = 0, len = res.data.length; j < len; j++) {
                    this.currentMsgData.push(JSON.parse(res.data[j]))
                }
            })
        },
        methods: {
            change(value) {
                if (!value) {
                    let $dom = this.$refs.btnForSendWrapperRef
                    // 退出
                    if (this.isSending) {
                        this.isSending = false
                        $dom.className = 'sendClose'
                    } else {
                        $dom.className = 'sendClose'
                        this.isOpen = false
                        this.isSending = true

                    }
                } else {
                    // 打开
                    this.isOpen = true
                    this.isSending = false
                }
            },
            toSend() {
                // 发送中，不能重复点击
                if (!this.sending) {
                    let $dom = this.$refs.btnForSendWrapperRef
                    let date = new Date()
                    let msg = new MsgBoard(this.currentSendName, this.currentContent, date)
                    if (msg.content !== '' && msg.content !== null) {
                        // 内容不为空，则发送
                        setOneMsgBoardData(msg)
                            .then(res => {
                                if (res.data) {
                                    return getAllMsgBoardData()
                                }
                            })
                            .then(res => {
                                this.currentMsgData = []
                                for (let j = 0, len = res.data.length; j < len; j++) {
                                    this.currentMsgData.push(JSON.parse(res.data[j]))
                                }
                                this.isSending = false
                                this.sending = false
                                $dom.className = 'sendClose'
                            })
                    } else {
                        // 如果输入内容为空，则退到留言区
                        this.isSending = false
                        $dom.className = 'sendClose'

                    }
                }

            },
            toSending() {
                let $dom = this.$refs.btnForSendWrapperRef
                $dom.className = 'sendOpen'
                this.isOpen = true
                this.isSending = true
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
        height: 100vh;
        width: 100vw;
        display: flex;
        justify-content: center;

        #btn {
            position: fixed;
            z-index: 100;
            @include flex;
            right: 110px;
            transform: translate(50%, -50%);

            top: 30px;
            height: 50px;
            width: 100px;
            @include blurWindow;

        }

        #shade {
            @include wrapper;
            @include bgBlur;
            z-index: 101;
            position: absolute;
        }

        .sendOpen {
            height: 80vh;
        }

        .sendClose {
            height: 120px;
        }

        #btnForSendWrapper {
            position: fixed;
            width: 100vw;
            display: flex;
            flex-direction: column;
            align-items: center;

            bottom: 0;

            z-index: 102;
            padding: 0;
            backdrop-filter: blur(3px);
            border-radius: 15px 15px 0 0;
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

                    &::-webkit-scrollbar {
                        display: none;
                    }


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
                width: 320px;
                border-radius: 15px;
                height: 55px;
                box-shadow: $shadow;
                cursor: pointer;
                font-size: 20px;
                transition: all .5s;
                font-weight: bolder;

                &:hover {
                    transform: scale(1.1);
                }
            }

        }

        #wrapper {
            width: 100%;
            height: 100vh;
            padding-top: 50px;
            box-sizing: border-box;
            position: fixed;
            display: flex;
            align-items: center;
            flex-direction: column;
            z-index: 101;
            overflow-y: scroll;

            &::-webkit-scrollbar {
                display: none;
            }

            .msgBlock {
                flex-shrink: 0;
                background-color: rgb(237, 237, 238);
                border-radius: 25px;
                width: 300px;
                overflow: hidden;
                margin: 5px 0 5px 0;


                transition: all .5s;

                &:hover {
                    transform: scale(1.05);
                }

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