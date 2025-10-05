import Course from "./Course";

export default function DetailCondition({data})
{
    let total;
    let complete;
    let content;
    let icon;
    let condition;
    if(data.condition == "ALL")
    {
        total = data.requirementCourseOption.length
        complete = data.requirementCourseOption.filter(c => c.status === true).length;
        condition = <div style={{fontSize:"0.75em"}} className="text-secondary mb-2">{total}개 과목 모두 이수 필요</div>
        if(data.progress == 100)
        {
            icon =  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-check2-circle text-success" viewBox="0 0 16 16">
                        <path d="M2.5 8a5.5 5.5 0 0 1 8.25-4.764.5.5 0 0 0 .5-.866A6.5 6.5 0 1 0 14.5 8a.5.5 0 0 0-1 0 5.5 5.5 0 1 1-11 0"/>
                        <path d="M15.354 3.354a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"/>
                    </svg>
            content =  <small style={{fontSize:"0.75em"}} className="ms-auto bg-success-subtle text-success-emphasis px-2 py-1 rounded-pill">{complete}/{total}개</small>
        }
        else
        {
            icon =  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-exclamation-circle text-warning" viewBox="0 0 16 16">
                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                        <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/>
                    </svg>
            content =  <small style={{fontSize:"0.75em"}} className="ms-auto bg-warning-subtle text-warning-emphasis px-2 py-1 rounded-pill">{complete}/{total}개</small>
        }
    }
    else if(data.condition == "MIN_CREDIT")
    {
        total = data.number
        complete = data.requirementCourseOption.reduce((sum, c) => {return sum + (c.status ? c.credit : 0)}, 0);
        condition = <div style={{fontSize:"0.75em"}} className="text-secondary mb-2">최소 {total}학점 이상 이수 필요</div>
        if(data.progress == 100)
        {
            icon =  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-check2-circle text-success" viewBox="0 0 16 16">
                        <path d="M2.5 8a5.5 5.5 0 0 1 8.25-4.764.5.5 0 0 0 .5-.866A6.5 6.5 0 1 0 14.5 8a.5.5 0 0 0-1 0 5.5 5.5 0 1 1-11 0"/>
                        <path d="M15.354 3.354a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"/>
                    </svg>
            content =  <small style={{fontSize:"0.75em"}} className="ms-auto bg-success-subtle text-success-emphasis px-2 py-1 rounded-pill">{complete}/{total}학점</small>
        }
        else
        {
            icon =  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-exclamation-circle text-warning" viewBox="0 0 16 16">
                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                        <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/>
                    </svg>
            content =  <small style={{fontSize:"0.75em"}} className="ms-auto bg-warning-subtle text-warning-emphasis px-2 py-1 rounded-pill">{complete}/{total}학점</small>        
        }
    }
    else if(data.condition == "K_OF")
    {
        total = data.number
        complete = data.requirementCourseOption.filter(c => c.status === true).length;
        condition = <div style={{fontSize:"0.75em"}} className="text-secondary mb-2">최소 {total}개 이상 이수 필요</div>
        if(data.progress == 100)
        {
            icon =  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-check2-circle text-success" viewBox="0 0 16 16">
                        <path d="M2.5 8a5.5 5.5 0 0 1 8.25-4.764.5.5 0 0 0 .5-.866A6.5 6.5 0 1 0 14.5 8a.5.5 0 0 0-1 0 5.5 5.5 0 1 1-11 0"/>
                        <path d="M15.354 3.354a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"/>
                    </svg>
            content =  <small style={{fontSize:"0.75em"}} className="ms-auto bg-success-subtle text-success-emphasis px-2 py-1 rounded-pill">{complete}/{total}개</small>
        }
        else
        {
            icon =  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-exclamation-circle text-warning" viewBox="0 0 16 16">
                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                        <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/>
                    </svg>
            content =  <small style={{fontSize:"0.75em"}} className="ms-auto bg-warning-subtle text-warning-emphasis px-2 py-1 rounded-pill">{complete}/{total}개</small>
        }

    }
    return(
        <div className="border border-secondary-subtle bg-body-tertiary rounded-4 shadow-sm mb-3 py-3">
            <div className="px-3 mb-3">
                <div className="d-flex align-items-center gap-0 column-gap-2">
                    <small className="fw-bold">{data.label}</small>
                    {icon}
                    {content}
                </div>
                {condition}
                <div className="progress" role="progressbar" aria-label="Basic example" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style={{height: "8px"}}>
                    <div className="progress-bar bg-dark" style={{width: `${data.progress}%`}}></div>
                </div>
            </div>
            <div className="d-grid gap-0 row-gap-1 px-3">
                {data.requirementCourseOption.map((value,index)=><Course data={value}></Course>)}
            </div>
        </div>
    )
}