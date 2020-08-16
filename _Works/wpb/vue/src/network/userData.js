import {request} from "./axios";

export function getBasicUserData() {
    return request({
        url: '/api/data/basic',
        method: 'post',
    })
}