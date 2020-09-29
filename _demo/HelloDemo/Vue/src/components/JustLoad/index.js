import JustLoad from './JustLoad'

let instance = null
let vue = null
JustLoad.install = Vue => {
    vue = Vue
    Vue.prototype.$justLoad = (to, e = 'app') => {
        switch (to) {
            case true:
                push(e)
                break
            case false:
                pull(e)
                break

        }
    }

}
let push = (e) => {
    let Constructor = vue.extend(JustLoad)
    instance = new Constructor().$mount()
    e.append(instance.$el)

}

let pull = (e) => {
    if (instance !== null) {
        e.removeChild(instance.$el)
        instance = null
        return true
    } else return false

}

export default JustLoad