import {request} from './axios.js'

export function getAllMsgBoardData() {
    return request({
        url: '/api/msgBoard/getAll',
        method: 'post',
    })
}


export function setOneMsgBoardData(obj) {
    return request({
        url: '/api/msgBoard/setOne',
        method: 'post',
        data: obj,
    })
}