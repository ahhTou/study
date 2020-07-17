import {request} from '../js/axios.js'

function getAllMsgBoardData() {
    return request({
        url: '/api/msgBoard/getAll',
        method: 'post',
    })
}


function setOneMsgBoardData(obj) {
    return request({
        url: '/api/msgBoard/setOne',
        method: 'post',
        data: obj,
    })
}

export default class MsgBoard {
    constructor(sendName, content, sendTime) {
        this.sendName = sendName
        this.content = content
        this.sendTime = sendTime
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

    static allList() {
        return new Promise((resolve, reject) => {
            let list = []
            getAllMsgBoardData().then(res => {
                for (let j = 0, len = res.data.length; j < len; j++) {
                    list.push(JSON.parse(res.data[j]))
                }
                resolve(list)
            })
        })
    }

    static setOne(obj) {
        return new Promise((resolve, reject) => {
            setOneMsgBoardData(obj).then(res => {
                if (res.data) {
                    resolve(true)
                }
            })
        })
    }

    updateSelf() {
        return new Promise((resolve, reject) => {
            setOneMsgBoardData(this).then(res => {
                if (res.data) {
                    resolve(true)
                }
            })
        })
    }

}