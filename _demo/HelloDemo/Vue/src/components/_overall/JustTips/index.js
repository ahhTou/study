import JustTips from './JustTips'
import {isEmpty} from '@/utils/Utils'

let instance = null
let vue = null
let app = document.getElementById('app')
JustTips.install = Vue => {
    vue = Vue
    vue.prototype.$justTips = (msg, style) => {
        if (isEmpty(style)) style = 'normal'
        if (instance === null) {
            create()
            instance.toShow(msg, style)
        } else {
            instance.showByCondition(msg, style)
        }
    }
}

let create = () => {
    let Constructor = vue.extend(JustTips)
    instance = new Constructor().$mount()

    document.body.append(instance.$el)
}

let destroy = () => {
    if (instance !== null) {
        app.removeChild(instance.$el)
    }
}


export default JustTips
