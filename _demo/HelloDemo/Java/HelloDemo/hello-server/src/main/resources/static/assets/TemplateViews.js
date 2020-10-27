export default {
    template: `
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
    
    `,
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
                '4xx': {
                    code: 'Err',
                    tip: '出现400异常',
                    color: '#ea495f'
                },
                '403': {
                    code: '403',
                    tip: '权限不够',
                    color: '#1e90ff'
                },
                'index': {
                    code: 'Hello',
                    tip: '欢迎来到ahhTou的后台',
                    color: '#48c9c4'
                },
                '500': {
                    code: '500',
                    tip: '服务器出现异常',
                    color: '#ff0024'
                },
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