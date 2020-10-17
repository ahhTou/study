export default function api_Solver(api) {
    return new Promise((resolve, reject) => {
        api.then(res => {
            resolve(res.data.data)
        }).catch(err => {
            reject(err.response.data.data.message)
        })
    })
}
