import MyAxios from 'assets/utils/axios'

let myAxios = new MyAxios()
myAxios.addInterceptorsForRequest(config => {
    console.log(config)
})