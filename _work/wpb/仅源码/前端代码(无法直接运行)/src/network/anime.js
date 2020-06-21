import {request} from "./axios";

export function getHowMuchPages() {
    return request({
        url: '/api/anime/pages',
        method: 'post',
    })

}

export function getOnePagesValue(url) {
    return request({
        url: '/api' + url,
        method: 'post',
    })
}

export function delOneAnimeById(id) {
    return request({
        url: '/api/anime/delOne',
        method: 'post',
        data: parseInt(id),
    })
}

export function getAllTypes() {
    return request({
        url: 'api/anime/getTypes',
        method: 'post'
    })
}

export function addOneAnime(obj) {
    return request({
        url: 'api/anime/addOne',
        method: 'post',
        data: obj
    })
}

export function updateOne(obj) {
    return request({
        url: 'api/anime/updateOne',
        method: 'post',
        data: obj
    })
}