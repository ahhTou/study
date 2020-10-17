export class HelloDom {
    el

    constructor(el) {
        this._el = el
    }
    get el() {
        return this._el
    }

    set el(value) {
        this._el = value
    }

    doCssAnime(classname) {
        this.el.classList.add(classname)
        this.el.onanimationend = () => {
            this.el.classList.remove(classname)
        }
    }

    doShake() {
        this.doCssAnime('shake-rotate')
    }

}
