import {request} from "./axios";

export function getHowMuchPages() {
    return request({
        url: '/api/anime/pages',
        method: 'post',
    })

}
