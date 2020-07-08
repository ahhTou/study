import axios from 'axios'

export function request(config) {

    let path = window.document.location.origin;
    const instance = axios.create({
        baseURL: path,
        headers: {
            'content-type': 'application/json;charset=UTF-8'
        }

    })
    return instance(config);
}
