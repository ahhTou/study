import {request} from "./axios";
import md5 from 'js-md5'

function authorize(obj) {
    return request({
        url: '/api/login/check',
        method: 'post',
        data: obj
    })

}
export default function (username, password) {
    let obj = {
        "username": username,
        "password": md5(password)
    }
    return authorize(obj)
}
