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

}