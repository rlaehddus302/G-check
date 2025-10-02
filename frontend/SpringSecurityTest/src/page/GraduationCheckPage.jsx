import SummaryField from "../component/GraduationCheckComponent/SummaryField";


export default function GraduationCheckPage()
{
  return(
    <div className={`flex-grow-1`}>
      <section className="h-100 container py-5 px-xl-5 px-lg-4 px-md-3 px-sm-1">
        <div className="mb-4">
          <div className="d-flex gap-0 column-gap-3">
            <div className="d-flex justify-content-center align-items-center">
              <div className={`d-flex align-items-center justify-content-center bg-gradient-custom rounded-4`} style={{width: "3.0rem", height: "3.0rem"}}>
                <svg style={{color:"#ad46ff"}} xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" className="bi bi-mortarboard" viewBox="0 0 16 16">
                  <path d="M8.211 2.047a.5.5 0 0 0-.422 0l-7.5 3.5a.5.5 0 0 0 .025.917l7.5 3a.5.5 0 0 0 .372 0L14 7.14V13a1 1 0 0 0-1 1v2h3v-2a1 1 0 0 0-1-1V6.739l.686-.275a.5.5 0 0 0 .025-.917zM8 8.46 1.758 5.965 8 3.052l6.242 2.913z"/>
                  <path d="M4.176 9.032a.5.5 0 0 0-.656.327l-.5 1.7a.5.5 0 0 0 .294.605l4.5 1.8a.5.5 0 0 0 .372 0l4.5-1.8a.5.5 0 0 0 .294-.605l-.5-1.7a.5.5 0 0 0-.656-.327L8 10.466zm-.068 1.873.22-.748 3.496 1.311a.5.5 0 0 0 .352 0l3.496-1.311.22.748L8 12.46z"/>
                </svg>
              </div>
            </div>
            <div className="d-flex justify-content-center align-items-center">
                <div className="fw-bolder fs-2"> 졸업 요건</div>
            </div>
          </div>
          <div className="text-secondary">현재 졸업 요건 달성 현황을 확인하고 부족한 부분을 파악해보세요</div> 
        </div>
        <div className="border border-secondary-subtle bg-white rounded-4 shadow-sm mb-3">
          <div className="py-3 d-grid gap-0 row-gap-4">
            <div className="px-3 d-flex align-items-center gap-0 column-gap-2">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-person fw-bold" viewBox="0 0 16 16">
                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
              </svg>   
              <div className="fw-bold">학생 정보</div>
            </div>
            <div className="row row row-cols-1 row-cols-md-4 gy-3 gx-md-3 gx-lg-5 px-3">
              <SummaryField label={"아이디"} value={localStorage.getItem("userID")}></SummaryField>
              <SummaryField label={"이름"} value={localStorage.getItem("name")}></SummaryField>
              <SummaryField label={"학과"} value={localStorage.getItem("major")}></SummaryField>
              <SummaryField label={"입학년도"} value={localStorage.getItem("admissionYear")}></SummaryField>
            </div>
          </div>
        </div>
        <div className={`d-grid gap-0 row-gap-3 border border-secondary-subtle bg-white rounded-4 shadow-sm py-3 mb-5`}>
            <div className='px-4 mt-2'>
                <div className='fw-semibold'>전체 진행률</div>
                <small className="text-secondary">총 120학점 중 117학점 이수 완료</small>
            </div>
            <div className='px-4'>
                <div style={{height:"12px"}} className="progress mb-2" role="progressbar" aria-label="Basic example" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
                    <div className={`progress-bar bg-dark`} style={{width:`75%`}}></div>
                </div>
                <div className="d-flex">
                  <small className='me-auto'>진행률 75%</small>
                  <small >남은 학점: 3학점</small>
                </div>
            </div>
            <div className='px-4 d-flex gap-0 column-gap-2 align-items-center'>
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-exclamation-circle text-warning" viewBox="0 0 16 16">
                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/>
              </svg>
              <div className="text-warning">졸업까지 3학점이 더 필요합니다</div>
            </div>
        </div>    
      </section>
    </div>
  )
}
