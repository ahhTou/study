import {request} from '../js/axios.js'

function api_sendVerificationEmail(email) {
    return request({
        url: '/api/account/sendVerificationEmail',
        method: 'post',
        data: email,
    })
}

function api_checkVerification(code) {
    return request({
        url: '/api/account/checkVerification',
        method: 'post',
        data: code,
    })
}


function api_checkUsernameUnique(username) {
    return request({
        url: '/api/account/checkUsernameUnique',
        method: 'post',
        data: username,
    })
}

function api_register(obj) {
    return request({
        url: '/api/account/register',
        method: 'post',
        data: obj,
    })
}

function api_regSuc(obj) {
    return request({
        url: '/api/account/regSuc',
        method: 'post',
        data: obj,
    })
}

function api_Solver(apiFuc, args, errT) {
    return new Promise((resolve, reject) => {
        apiFuc(args).then(res => {
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
        }, err => {
            reject('请求发生异常' + err)
        })
    })
}

export default class User {
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

    static sendVerificationEmail(email) {
        return api_Solver(api_sendVerificationEmail, email, '邮箱已存在')

    }

    static checkVerification(obj) {
        return api_Solver(api_checkVerification, obj, '验证码错误')

    }

    static checkUsernameUnique(obj) {
        return api_Solver(api_checkUsernameUnique, obj, '用户名已存在')
    }

    static register(obj) {
        return api_Solver(api_register, obj, '注册失败')
    }

    static regSuc(obj) {
        return api_Solver(api_regSuc, obj, '获取失败')
    }

}