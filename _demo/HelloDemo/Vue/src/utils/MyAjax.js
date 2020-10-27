import axios from 'axios'
import {getAuthToken} from '@/utils/LocalSaver'

export class HelloAxios {

    baseURL = window.document.location.origin
    headers = {
        'content-type': 'application/json;charset=UTF-8'
    }
    proxy_path = ''
    instance = axios.create({
        baseURL: this.baseURL,
        headers: this.headers,
    })

    constructor() {
        this.instance.interceptors.request.use(
            config => {
                if (getAuthToken()) {
                    config.headers['Token-Head-Key'] = getAuthToken()
                } else {
                }
                return config
            }
        )
    }

    post = (url, data) => {
        return this.instance({
            url,
            data,
            method: 'post',
        })
    }

    get = (url, data) => {
        return this.instance({
            url,
            data,
            method: 'get',
        })
    }
}

export function request(config) {
    let path = window.document.location.origin
    const instance = axios.create({
        baseURL: path,
        headers: {
            'content-type': 'application/json;charset=UTF-8'
        }

    })

    console.log(instance)
    /*    /!* 拦截器 *!/
        instance.interceptors.request.use(
            config => {
                if (localStorage.getItem('token')) {
                    config.headers.token = localStorage.getItem('token')
                }
                return config
            },
            error => {
                return Promise.reject(error)
            })
        instance.interceptors.response.use(
            response => {
                /!*            if (response.config.url === '/api/login/check') {
                                if (response.data === "null" || response.data === null || response.data === '') {
                                    return Promise.reject("密码错误或者账户名错误")
                                }
                            }*!/
                return response
            },
            error => {
                if (error.response.status === 500) {
                    return Promise.reject('发送登录请求失败 err: 500')
                } else {
                    return Promise.reject(error.response.status) // 返回接口返回的错误信息
                }
            })*/
    return instance(config)
}
