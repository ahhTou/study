<template>
    <div id="MsgBoardWrapper">
        <shade-slots>
            <div id="forLoading" style="width: 100vw;height: 100vh;position: fixed;"></div>
            <div id="wrapperForMsgBoard" :class="onBlurClassName">
                <div v-for="item in currentMsgData" class="msgBlock" @click.stop>
                    <div class="sends">
                        <div class="sendName">{{ item.sendName | msgBoardSenderFilter }}</div>
                        <div class="sendTime">{{ item.sendTime | timeFilter }}</div>
                    </div>
                    <div class="content">{{ item.content }}</div>
                </div>
            </div>

            <fb-ctrl/>

        </shade-slots>
    </div>
</template>


<script>
import FeedBack from 'assets/Bean/FeedBack'
import MyFilter from 'assets/utils/MyFilter'
import ShadeSlots from 'components/ShadeSlots'
import FbCtrl from 'components/Feedback/FbCtrl'
import FbBus from 'components/Feedback/FbBus'

export default {
    name: 'Feedback',
    components: {FbCtrl, ShadeSlots},
    data() {
        return {
            isSending: false,

            currentMsgData: ['', '', '', '', '', '', '', '', '', '',],
            currentSendName: null,
            currentContent: null,

            sendingLock: false, //负责锁定发送

            btnForSendWrapperClassName: 'sendClose',
            btnForSendText: '点击此处开始留言',

            onBlurClassName: {
                onBlur: false,
            },

        }
    },
    mounted() {

        FbBus.$on('onBlur', () => {
            this.onBlurClassName.onBlur = !this.onBlurClassName.onBlur
            this.refresh()
        })
        this.refresh()

    },
    methods: {
        refresh: function () {
            new FeedBack().allList().then(res => {
                this.currentMsgData = res
            })
        },
    },
    filters: {
        timeFilter: date => {
            return MyFilter.timeFilter(date)
        },
        msgBoardSenderFilter: (name) => {
            if (name === null) return '匿名用户'
            else return name
        }
    },
}
</script>

<style lang="scss" scoped>
@import "var";

#wrapperForMsgBoard {
    position: relative;
    transition: all .3s;
}

.onBlur {
    transition: all .3s;
    filter: blur(10px);
}

.sendOpen {
    height: 70vh;
}

.sendClose {
    height: 120px;
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
                color: $color;
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
