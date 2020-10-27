import {HelloAxios} from '@/utils/MyAjax'
import api_Solver from '@/utils/ResultHandler'

let path = {
    checkLogin: '/api/auth/checkLogin'
}

export function checkLogin() {
    let helloAxios = new HelloAxios()
    let axiosPromise = helloAxios.get(path.checkLogin)

    return api_Solver(axiosPromise)

}
