import DetailCondition from "./DetailCondtion";
import Donut from "./Donut";

export default function RequirementProgressCard({data})
{
    let icon;
    let color;
    let backgroundColor;
    let content;
    if(data.progress != 100)
    {
        icon = <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-exclamation-circle text-warning ms-auto" viewBox="0 0 16 16">
                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                    <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/>
                </svg>
        color = 'var(--bs-danger)'
        backgroundColor = "var(--bs-danger-bg-subtle)"
        content = <small style={{fontSize:"0.75em"}} className="ms-auto bg-warning-subtle text-warning-emphasis px-2 py-1 rounded-pill">부족</small>
    }
    else
    {
        icon =  <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-check2-circle text-success ms-auto" viewBox="0 0 16 16">
                    <path d="M2.5 8a5.5 5.5 0 0 1 8.25-4.764.5.5 0 0 0 .5-.866A6.5 6.5 0 1 0 14.5 8a.5.5 0 0 0-1 0 5.5 5.5 0 1 1-11 0"/>
                    <path d="M15.354 3.354a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"/>
                </svg>
        color = 'var(--bs-success)'
        backgroundColor = "var(--bs-success-bg-subtle)"
        content = <small style={{fontSize:"0.75em"}} className="ms-auto bg-success-subtle text-success-emphasis px-2 py-1 rounded-pill">완료</small>
    }
    return(
        <div className="border border-secondary-subtle bg-white rounded-4 shadow-sm mb-3 py-3 h-100">
            <div className="px-3 d-grid gap-0 row-gap-2">
                <div className="d-flex align-items-center gap-0 column-gap-2">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-book fw-bold" viewBox="0 0 16 16">
                        <path d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783"/>
                    </svg>
                    <div className="fw-bold">{data.label}</div>
                    {icon}
                </div>
                <small className="text-secondary">{data.completedCredits}/{data.requiredCredits}학점 이수</small>
            </div>
            <div className="d-flex align-items-center justify-content-center px-3 mb-3">
                <Donut value={data.progress} size={120} color={color} backgroundColor={backgroundColor}></Donut>
            </div>
            <div className="px-3 d-flex align-items-center">
                <small className="text-secondary">{data.completedCredits}/{data.requiredCredits}학점</small>
                {content}
            </div>
            {
                data.detailedCompletionRequirements.length == 0 ? <></>
                :
                <div className="px-3">
                    <hr />
                    <div className="mb-2">
                        <small className="fw-bold">세부 이수 조건</small>
                    </div>
                    {data.detailedCompletionRequirements.map((value, index) => <DetailCondition data={value} />)}
                </div>
            }
        </div>
    )
}