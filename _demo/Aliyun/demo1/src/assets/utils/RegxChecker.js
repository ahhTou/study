export default class RegxChecker {

    static emailRegx = '^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$'

    static usernameRegx = '^[a-zA-Z0-9_-\u4e00-\u9fa5]{4,16}$'

    static passwordRegx = '^.*(?=.{6,})(?=.*\\d)(?=.*[a-z]).*$'

    static checkEmail = (email) => {

        if (email != null) {
            email = email.trim()
            let res = email.match(this.emailRegx)
            return res !== null
        } else {
            return false
        }

    }
    static checkUsername = (username) => {
        if (username != null) {
            username = username.trim()
            let res = username.match(this.usernameRegx)
            return res !== null
        } else {
            return false
        }
    }
    static checkPassword = (password) => {
        if (password != null) {
            password = password.trim()
            let res = password.match(this.passwordRegx)
            return res !== null
        } else {
            return false
        }
    }

}