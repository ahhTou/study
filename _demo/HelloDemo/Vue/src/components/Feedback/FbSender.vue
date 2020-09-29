<template>
    <transition name="fade0">
        <div id="fb-sender-wrapper" v-if="isOpen">
            <div id="form-wrapper">
                <span>昵称</span>
                <label>
                    <input id="nameArea" type="text" v-model="MSG.sendName">
                </label>
                <span>留言 or 反馈</span>
                <label>
                    <textarea id="textArea" v-model="MSG.content"/>
                </label>
                <div id="btn-line">
                    <button @click="close" class="icon iconfont icon-back back-btn"></button>
                    <button @click="send" :class="sendClassName">提交</button>
                </div>
            </div>
        </div>
    </transition>
</template>

<script>
import FeedBack from 'assets/pojo/FeedBack'
import FbBus from 'components/Feedback/FbBus'

export default {
    name: 'FbSender',
    props: {
        isOpen: false,
    },
    data() {
        return {
            MSG: new FeedBack(),
            isActive: false,
        }
    },
    computed: {
        sendClassName() {
            return {
                'send-active': !this.isActive,
            }
        }
    },
    watch: {
        MSG: {
            handler: function (val) {
                this.isActive = (val.content !== null && val.content !== '')
            },
            deep: true,
        }
    },
    methods: {
        send: function () {
            if (this.isActive) {
                this.MSG.update().then(res => {
                    console.log(res)
                    this.MSG = new FeedBack()
                    this.close()
                })
            } else {

            }
        },
        close: function () {
            FbBus.$emit('closeSender')
        }
    },
    mounted() {

    }
}
</script>

<style lang="scss" scoped>
@import "var";

#fb-sender-wrapper {
    width: $senderWidth;
    height: $senderWidth;
    border-radius: 25px;
    box-shadow: 5px 5px 20px #474747;
    overflow: hidden;
    @include flex;
    @include trans;
    background: $color;

    #form-wrapper {
        position: relative;
        color: white;
        $formWidth: 250px;
        display: flex;
        flex-direction: column;
        align-items: center;

        span {
            width: $formWidth;

            &:nth-child(1) {
                text-align: right;
            }

            &:nth-child(5) {
                text-align: center;
            }
        }

        #btn-line {
            display: flex;
            width: $formWidth;

            button {
                height: 40px;
                border-radius: 3px;

                &:nth-child(1) {
                    box-sizing: border-box;
                    border: white 1px solid;
                    width: 80px;
                    color: white;
                    margin-right: 5px;
                }

                &:nth-child(2) {
                    background: white;
                    flex: 1 0 auto;
                    margin-left: 5px;
                }
            }

            .send-active {
                opacity: .7;
            }
        }


        textarea {
            width: $formWidth;
            margin: 10px;
            font-size: 1rem;
            border: none;
            resize: none;
            outline: none;
            border-radius: 3px;
            padding: 1rem;
            box-sizing: border-box;

        }

        #textArea {
            height: 100px;
        }

        #nameArea {
            font-size: 20px;
            border-radius: 3px;
            padding: 5px;
            box-sizing: border-box;
            margin: 10px;
            height: 30px;
            width: $formWidth;
        }

    }

    #btnForSend {
        position: absolute;
        @include flex;
        color: white;
        background: $color;
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
</style>