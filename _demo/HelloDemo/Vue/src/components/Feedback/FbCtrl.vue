<template>
    <div id="fb-ctrl-wrapper" @click.stop>
        <div id="content-wrapper" @click.stop>
            <div id="btn" @click.stop="toSender" :class="btnClassName"
            >
                <btn-circle
                    :is-active="true"
                    :color="btnColor[0]"
                    size="80px"
                    :bg-color-active="btnColor[1]"
                    btn-svg-style="icon-feedback"
                    show-shadow="5px 5px 10px #474747"
                />
            </div>

            <div id="sender" @click.stop>
                <fb-sender :is-open="fbSenderIsOpen"/>
            </div>
        </div>
    </div>
</template>

<script>
import BtnCircle from 'components/BtnCircle'
import FbSender from 'components/Feedback/FbSender'
import FbBus from 'components/Feedback/FbBus'

export default {
    name: 'FbCtrl',
    components: {FbSender, BtnCircle},
    data() {
        return {
            btnClassName: {
                'btn-normal': true,
                'btn-sending': false,
            },
            fbSenderIsOpen: false,
            btnColor: ['white', 'rgb(234,73,95)']
        }
    },
    mounted() {
        FbBus.$on('closeSender', () => {
            this.toSender()
        })
    },
    methods: {
        toSender: function () {
            this.fbSenderIsOpen = !this.fbSenderIsOpen
            ;[this.btnColor[0], this.btnColor[1]] = [this.btnColor[1], this.btnColor[0]]
            this.btnClassName['btn-sending'] = !this.btnClassName['btn-sending']
            FbBus.$emit('onBlur')
        }
    }
}
</script>

<style lang="scss" scoped>
@import "var";


$btnWidth: 40px;

#fb-ctrl-wrapper {
    @include flex;
    position: absolute;
    width: 100vw;
    height: 100vh;
    pointer-events: none;

    #content-wrapper {
        position: fixed;
        @include flex;
        width: $senderWidth;
        height: $senderWidth;

        #sender {
            z-index: 1;
        }

        #btn {
            position: absolute;
            z-index: 2;
            bottom: 0;
            right: 0;
            pointer-events: all;
        }
    }

}

.btn-normal {
    @include trans;
    transform: translate($btnWidth/2, $btnWidth/2);
}

.btn-sending {
    @include trans;
    will-change: transform;
    transform: translate(-$senderWidth + $btnWidth*1.5, -$senderWidth + $btnWidth*1.5);
}

</style>