import { useRouteLoaderData } from "react-router-dom"

export default function OtherRequirements()
{
    let data = useRouteLoaderData("gradRequir")
    let name;    
    if(data.condition == "K_OF")
    {
        name = `아래 선택지 중 택${data.number}`
    }
    else if(data.condition == "ALL")
    {
        name = "졸업 연구 요건"
    }

    return (
        <div>
            <div className="d-flex gap-0 column-gap-3 mb-3">
                <div className="d-flex align-items-center">
                    <svg style={{color:"#ff6900"}} xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" className="bi bi-mortarboard" viewBox="0 0 16 16">
                        <path d="M8.211 2.047a.5.5 0 0 0-.422 0l-7.5 3.5a.5.5 0 0 0 .025.917l7.5 3a.5.5 0 0 0 .372 0L14 7.14V13a1 1 0 0 0-1 1v2h3v-2a1 1 0 0 0-1-1V6.739l.686-.275a.5.5 0 0 0 .025-.917zM8 8.46 1.758 5.965 8 3.052l6.242 2.913z"/>
                        <path d="M4.176 9.032a.5.5 0 0 0-.656.327l-.5 1.7a.5.5 0 0 0 .294.605l4.5 1.8a.5.5 0 0 0 .372 0l4.5-1.8a.5.5 0 0 0 .294-.605l-.5-1.7a.5.5 0 0 0-.656-.327L8 10.466zm-.068 1.873.22-.748 3.496 1.311a.5.5 0 0 0 .352 0l3.496-1.311.22.748L8 12.46z"/>
                    </svg>
                </div>
                <div className="fw-bold fs-3">기타 졸업 조건</div>
            </div>
            <div className="row row-cols-1 row-cols-lg-2 row-cols-xl-3 g-4">
                <div className="col">
                    <div style={{borderColor:"#ffd7a8"}} className="card py-3 h-100">
                        <div style={{backgroundColor:"#fff7ed"}} className="card-header border-0 mb-5">
                            <div style={{color:"#9f2d00"}} className="fw-bold">학점 및 성적</div>
                            <div className="text-secondary" style={{fontSize:"0.9em"}}>기본 학업 요건</div>
                        </div>
                        <div className="card-body px-0">
                            <div className="h-100 d-grid gap-0 row-gap-3">
                                <div style={{backgroundColor:"#fff7ed"}} className="text-center py-1 d-flex align-items-center justify-content-center">
                                    <div>
                                        <div style={{color:"#f54a00"}} className="fw-bold fs-4">{data.totalCredit}학점</div>
                                        <div>총 이수 학점</div>
                                    </div>
                                </div>
                                <div style={{backgroundColor:"#fff7ed"}} className="text-center py-1 d-flex align-items-center justify-content-center">
                                    <div>
                                        <div style={{color:"#f54a00"}} className="fw-bold fs-4">{data.averageScore.toFixed(1)}</div>
                                        <div>최소 평점 평균</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div style={{borderColor:"#e9d4ff"}} className="card py-3 h-100">
                        <div style={{backgroundColor:"#faf5ff"}} className="card-header border-0 mb-5">
                            <div style={{color:"#6e11b0"}} className="fw-bold">어학 요건</div>
                            <div className="text-secondary" style={{fontSize:"0.9em"}}>외국어 능력 증명</div>
                        </div>
                        <div className="card-body px-0">
                            <div className="h-100 d-grid gap-0 row-gap-3">
                                <div style={{backgroundColor:"#faf5ff"}} className="text-center py-1 d-flex align-items-center justify-content-center">
                                    <div>
                                        <div style={{color:"#6e11b0"}} className="fw-bold fs-4">TOEIC {data.toeic}</div>
                                        <div>또는 동등 수준</div>
                                    </div>
                                </div>
                                <div style={{backgroundColor:"#faf5ff"}} className="text-center py-1 d-flex align-items-center justify-content-center">
                                    <div>
                                        <div style={{color:"#6e11b0"}} className="fw-bold fs-4">영어강의 {data.requireEnglishCourse}과목</div>
                                        <div>전공 {data.requireMinimumEnglishMajorCourse}과목 이상</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div style={{borderColor:"#bedbff"}} className="card py-3 h-100">
                        <div style={{backgroundColor:"#eff6ff"}} className="card-header border-0 mb-5">
                            <div style={{color:"#193cb8"}} className="fw-bold">졸업 논문/작품</div>
                            <div className="text-secondary" style={{fontSize:"0.9em"}}>{name}</div>
                        </div>
                        <div className="card-body px-0">
                            <div className="h-100 d-grid gap-0 row-gap-3">
                                {data.others.map((value, index)=>{
                                    return(
                                    <div style={{backgroundColor:"#eff6ff"}} className="text-center py-1 d-flex align-items-center justify-content-center">
                                        <div style={{color:"#155dfc"}} className="text-center fw-bold fs-4" >{value.content}</div>
                                    </div>
                                    )
                                })}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}