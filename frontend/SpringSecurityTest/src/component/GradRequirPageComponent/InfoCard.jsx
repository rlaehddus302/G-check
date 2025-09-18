
export default function InfoCard({data})
{
    let content;
    if(data.children.length > 0)
    {
        content =   data.children.map((value, index) => 
                    {
                        let condition;
                        if(value.condition == "ALL")
                        {
                            condition = "아래 과목 필수"
                        }
                        else if(value.condition == "MIN_CREDIT")
                        {
                            condition = `아래 중 ${value.number}학점 이상`
                        }
                        else if(value.condition == "K_OF")
                        {
                            condition = `아래 과목 중 택 ${value.number}`
                        }
                        return(
                        <div style={{backgroundColor:"#fff7ed"}} className="py-1 px-2">
                            <div class="rounded-3 bg-body-secondary-subtle p-3 d-flex align-items-center gap-2">
                                <div>
                                    <div style={{color:"#f54a00", fontSize:"0.9em"}} className="fw-bold">{value.category} ({condition})</div>
                                    {
                                        value.standardCourses.map(value => 
                                            <small class="text-secondary">{value.course.name}<br/></small>
                                            
                                        )
                                    }
                                </div>
                            </div>
                        </div>
                    )})
    }
    else
    {
        content =   <div style={{backgroundColor:"#fff7ed"}} className="py-1 px-2">
                        <div class="rounded-3 bg-body-secondary-subtle p-3 d-flex align-items-center gap-2">
                            <div>
                                <div style={{color:"#f54a00", fontSize:"0.9em"}} className="fw-bold">세부 조건 없음</div>
                                <small class="text-secondary">필수 과목 : 없음<br/></small>
                                <small class="text-secondary">선택 과목 : 없음<br/></small>
                                <small class="text-secondary">최소 평점 : 제한 없음<br/></small>
                                <small class="text-secondary">충족 조건 : 학점 요건만 달성<br/></small>
                            </div>
                        </div>
                    </div>

    }
    return(
        <div className="col">
            <div style={{borderColor:"#ffd7a8"}} className="card py-3 h-100">
                <div className="d-grid gap-0 row-gap-3">
                    <div style={{backgroundColor:"#fff7ed", height:"5em"}} className="card-header border-0 d-flex">
                        <div className="my-auto">
                            <div style={{color:"#9f2d00"}} className="fw-bold text-break">{data.category}</div>
                            <div className="text-secondary text-break" style={{fontSize:"0.9em"}}>{data.remarks}</div>
                        </div>
                    </div>
                    <div className="card-body px-0">
                        <div className="h-100 d-grid gap-0 row-gap-3">
                            <div className="text-center py-1 d-flex align-items-center justify-content-center">
                                <div>
                                    <div style={{color:"#f54a00"}} className="fw-bold fs-3">{data.number}학점</div>
                                </div>
                            </div>
                            { content }
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}