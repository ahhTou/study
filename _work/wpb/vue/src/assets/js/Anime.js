export default class Anime {

    constructor(id, title, imgUrl, types, endTime) {
        this.id = id
        this.title = title
        this.imgUrl = imgUrl
        this.types = types
        this.endTime = endTime
    }

    get getId() {
        return this.id;
    }

    set setId(value) {
        this.id = value;
    }

    get getTitle() {
        return this.title;
    }

    set setTitle(value) {
        this.title = value;
    }

    get getImgUrl() {
        return this.imgUrl;
    }

    set setImgUrl(value) {
        this.imgUrl = value;
    }

    get getTypes() {
        return this.types;
    }

    set setTypes(value) {
        this.types = value;
    }

    get getEndTime() {
        return this.endTime;
    }

    get getEndTimeForInput() {
        return this.timeFilter(this.getEndTime)
    }

    set setEndTime(value) {
        this.endTime = value
    }

    set setEndTimeForInput(value) {
        let _timeFormat = value.replace(/-/g, '/')
        this.endTime = new Date(_timeFormat).getTime();
    }

    getString() {
        console.log(JSON.stringify(this))
    }

    timeFilter(date) {
        let func = (i) => {

            if (i === 0) return '01'
            if (i < 10) return '0' + i
            if (i >= 10) return i

        }
        if (date === null || date === undefined || date === '') {

            return null

        } else {
            let DATE = new Date(date)
            let fullYear = DATE.getFullYear()
            let month = func(DATE.getMonth() + 1)
            let day = func(DATE.getDate())
            return fullYear + '-' + month + '-' + day
        }
    }
}