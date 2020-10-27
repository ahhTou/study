export default class MyFilter {
    static timeFilter(date) {
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