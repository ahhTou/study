import TipsView from './TipsView'

let instance = null
let vue = null
TipsView.install = function (Vue) {
    vue = Vue
    vue.prototype.$myTipsViews = function (str, args) {
        switch (str) {
            case 'open':
                createInstance(args)
                break
            case 'close':
                closeTipsAfterAnime()
                break
        }
    }

}

let createInstance = (args) => {
    if (instance === null) {
        let Constructor = vue.extend(TipsView)
        instance = new Constructor().$mount()
        instance.args = args
        document.body.append(instance.$el)
    } else {
        document.body.removeChild(instance.$el)
        instance === null
        createInstance(args)
    }
}

let closeTipsAfterAnime = () => {
    instance.isOpen = false
    setTimeout(() => {
        document.body.removeChild(instance.$el)
        instance = null
    }, 200)
}

export default TipsView
