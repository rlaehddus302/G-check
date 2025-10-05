
export default function Course({data})
{
    let icon;
    if(data.status)
    {
        icon =  <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" fill="currentColor" className="bi bi-check2-circle text-success" viewBox="0 0 16 16">
                    <path d="M2.5 8a5.5 5.5 0 0 1 8.25-4.764.5.5 0 0 0 .5-.866A6.5 6.5 0 1 0 14.5 8a.5.5 0 0 0-1 0 5.5 5.5 0 1 1-11 0"/>
                    <path d="M15.354 3.354a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"/>
                </svg>
    }
    else
    {
        icon = <div style={{width:"12px", height:"12px"}} className="rounded-circle border border-secondary"></div>
    }
    return(
        <div className="d-flex gap-0 column-gap-2 align-items-center">
            {icon}
            <div style={{fontSize:"0.75em"}}>{data.courseName}({data.credit}학점)</div>
        </div>
    )
}