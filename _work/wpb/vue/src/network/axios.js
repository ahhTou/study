import axios from 'axios'

export function request(config) {
    let path = window.document.location.origin;
    const instance = axios.create({
        baseURL: path,
    })
    instance.interceptors.request.use(
        config => {
            if (localStorage.getItem('token')) {
                config.headers.token = localStorage.getItem('token');
            }
            return config;
        },
        error => {
            return Promise.reject(error);
        });
    instance.interceptors.response.use(
        response => {
            // console.log(response.data)
            if (response.config.url === '/api/login/check') {
                if (response.data === "null" || response.data === null || response.data === '') {
                    return Promise.reject("密码错误或者账户名错误")
                }
            }
            if (response.config.url === '/api/data/basic') {
                if (response.data === "null" || response.data === null || response.data === '') {
                    return Promise.reject("登录过期，重新登录")
                }
            }
            return response
        },
        error => {
            return Promise.reject(error.response.status) // 返回接口返回的错误信息
        })
    return instance(config);
}
