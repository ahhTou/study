import {HelloAxios} from '@/utils/MyAjax'

export default class FeedBack {
    sendName
    content
    sendTime

    constructor(sendName, content, sendTime) {
        this.sendName = sendName
        this.content = content
        this.sendTime = sendTime
    }

    axios = new HelloAxios()


    allList() {
        let api = this.axios.cPost('/api/msgBoard/getAll', null)
        return new Promise(resolve => {
            let list = []
            api.then(res => {
                for (let j = 0, len = res.data.length; j < len; j++) {
                    list.push(JSON.parse(res.data[j]))
                }
                resolve(list)
            })
        })
    }

    update() {
        this.sendTime = new Date()
        let api = this.axios.cPost('/api/msgBoard/setOne', this)
        return new Promise((resolve, reject) => {
            api.then(res => {
                if (res.data) {
                    resolve(true)
                }
            })
        })
    }


    get getSendName() {
        return this.sendName
    }

    set setSendName(value) {
        this.sendName = value
    }

    get getContent() {
        return this.content
    }

    set setContent(value) {
        this.content = value
    }

    get getSendTime() {
        return this.sendTime
    }

    set setSenTime(value) {
        this.sendTime = value
    }

}
