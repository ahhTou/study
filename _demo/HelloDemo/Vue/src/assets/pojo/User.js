import UtilsFactory from 'assets/factory/UtilsFactory'

export default class User {

    passwordR = null

    axios = UtilsFactory.getAxios()

    apiUrl = {
        sendVerificationEmail: '/api/account/sendVerificationEmail',
        checkVerification: '/api/account/checkVerification',
        checkUsernameUnique: '/api/account/checkUsernameUnique',
        register: '/api/account/register',
        regSuc: 'regSuc',
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
        let axiosPromise = this.axios.cPost(this.apiUrl.sendVerificationEmail, this.email)
        return api_Solver(axiosPromise, '邮箱已存在')

    }

    checkVerification = () => {
        let axiosPromise = this.axios.cPost(this.apiUrl.checkVerification, this)
        return api_Solver(axiosPromise, '验证码错误')
    }

    checkUsernameUnique = () => {
        let axiosPromise = this.axios.cPost(this.apiUrl.checkUsernameUnique, this)
        return api_Solver(axiosPromise, '用户名已存在')
    }

    register = () => {
        let axiosPromise = this.axios.cPost(this.apiUrl.register, this)
        return api_Solver(axiosPromise, '注册失败')
    }

    regSuc = () => {
        let axiosPromise = this.axios.cPost(this.apiUrl.regSuc, this.username)
        return api_Solver(axiosPromise, '获取失败')
    }

}


function api_Solver(api, errT) {
    return new Promise(resolve => {
        api.then(res => {
            switch (res.data) {
                case true:
                    resolve(true)
                    break
                case false:
                    resolve(errT)
                    break
                case 'err':
                    resolve('服务器发生错误')
                    break
                default:
                    resolve(res.data)
            }
        }, () => {
            resolve('失去连接')
        })
    })
}

