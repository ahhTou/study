const nums = [1, 2, 3, 4, 5]
const list = nums.map(n => {
    return (
        <li key={n}> {n} </li>
    )
})

ReactDOM.render(
    <ul>
        {list}
    </ul>,
    document.getElementById('root')
)
