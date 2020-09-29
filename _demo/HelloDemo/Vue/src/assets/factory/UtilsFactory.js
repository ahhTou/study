import MyAxios from 'assets/utils/MyAjax'

export default class UtilsFactory {

    static myAxios

    static create = () => {
        this.myAxios = new MyAxios()
    }

    static getAxios = () => {
        return UtilsFactory.myAxios
    }


}