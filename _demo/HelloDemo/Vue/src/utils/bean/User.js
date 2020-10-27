import ErrHandler from '@/utils/ResultHandler'
import {HelloAxios} from '@/utils/MyAjax'
import {getAuthToken} from '@/utils/LocalSaver'

export default class User {

    passwordR = null

    axios = new HelloAxios()

    apiUrl = {
        sendVerificationEmail: '/api/account/sendVerificationEmail',
        checkVerification: '/api/account/checkVerification',
        checkUsernameUnique: '/api/account/checkUsernameUnique',
        register: '/api/account/register',
        login: '/api/account/toLogin',
        regSuc: '/api/account/regSuc',
    }

    code = null

    constructor(id, email, username, password, nickname) {
        this.id = id
        this.username = username
        this.nickname = nickname
        this.password = password
        this.email = email
    }

    setEC(email, code) {
        this.email = email
        this.code = code
    }

    sendVerificationEmail = () => {
        let axiosPromise = this.axios.post(this.apiUrl.sendVerificationEmail, this.email)
        return ErrHandler(axiosPromise)

    }

    checkVerification = () => {
        let axiosPromise = this.axios.post(this.apiUrl.checkVerification, this)
        return ErrHandler(axiosPromise)
    }

    checkUsernameUnique = () => {
        let axiosPromise = this.axios.post(this.apiUrl.checkUsernameUnique, this)
        return ErrHandler(axiosPromise)
    }

    register = () => {
        let axiosPromise = this.axios.post(this.apiUrl.register, this)
        return ErrHandler(axiosPromise)
    }

    regSuc = () => {
        let axiosPromise = this.axios.post(this.apiUrl.regSuc, this.username)
        return ErrHandler(axiosPromise)
    }

    login = () => {
        let axiosPromise = this.axios.post(this.apiUrl.login, this)
        return ErrHandler(axiosPromise)
    }

}

