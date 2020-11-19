import {isEmpty} from '@/utils/Utils'

let AUTH_TOKEN_KEY = 'Token-Head-Key'

export function saveAuthToken(token) {
    window.localStorage.setItem(AUTH_TOKEN_KEY, token)
}

export function getAuthToken() {
    let token = window.localStorage.getItem(AUTH_TOKEN_KEY)
    if (isEmpty(token)) return false
    else return token
}

export function removeAuthToken() {
    window.localStorage.removeItem(AUTH_TOKEN_KEY)
}
