<template>
    <div id="errViewsWrapper" :style="bg">
        <div id="wrapper">
            <div id="code">
                <slot>{{ status[current].code }}</slot>
            </div>
            <div id="tip">
                <slot>{{ status[current].tip }}</slot>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'ErrViews',
    props: {
        current: {
            default: '404'
        }
    },
    data() {
        return {
            status: {
                '404': {
                    code: '404',
                    tip: '未找到界面',
                    color: '#ea495f'
                },
                '403': {
                    code: '403',
                    tip: '权限不够',
                    color: '#1e90ff'
                }
            },
        }
    },
    computed: {
        bg() {
            return {
                'background-color': this.status[this.current].color
            }
        }
    },
}
</script>

<style lang="scss" scoped>
#errViewsWrapper {
    display: flex;
    align-items: center;
    justify-content: center;
    position: fixed;

    #wrapper {
        height: 100vh;
        width: 100vw;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        color: white;

        #code {
            font-size: 20vw;
        }

        #tip {
            font-size: 3vw;
        }

    }

}
</style>