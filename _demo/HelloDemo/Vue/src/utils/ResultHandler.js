export default function api_Solver(api) {
    return new Promise((resolve, reject) => {
        api.then(res => {
            resolve(res.data.data)
        }).catch(err => {
            reject(err.response.data.data.data)
        })
    })
}

export function result(res) {
    if (res.data.success) {
        return res.data.data
    }
}
