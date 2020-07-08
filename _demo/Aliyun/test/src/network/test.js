import {request} from "./axios";

export function ajax() {
    return request({
        url: 't1/hello',
        method: 'post',
    })

}