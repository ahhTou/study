export default class Base {

    static BgListApiUrl = '/api/base/getBgList'

    BG_URLS
    size
    suffix = '_bg_changer'

    static suffixPro = '_bg_changer'


    constructor(data) {
        this.BG_URLS = data.bgList
        this.size = data.size

    }

    getMobileUrl() {
        return this.BG_URLS
            .filter(list => list.indexOf('mobile') !== -1)
            .filter(list => list.indexOf(this.suffix) !== -1)
    }

    getPcUrl() {
        return this.BG_URLS
            .filter(list => list.indexOf('pc') !== -1)
            .filter(list => list.indexOf(this.suffix) !== -1)

    }

    getWidth() {
        return this.size.width
    }

    getHeight() {
        return this.size.height
    }

    static getTrueUrl(url) {
        return url.replace(this.suffixPro, '')
    }
}