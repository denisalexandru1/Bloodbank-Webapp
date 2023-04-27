function DemoPage(){
    fetch("http://localhost:8080/donor").then(res => { 
    console.log(res);
    res.json().then(body => {
        console.log(body);
    })
    }
    )
    
    return (
        <div>Demo Page is working</div>
    )
}

export default DemoPage;