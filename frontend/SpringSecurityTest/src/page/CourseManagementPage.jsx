import { useState } from "react"
import { semesters, years } from "../util/AcademicTerm"
import DropDown from "../component/CourseManagementComponent/DropDown"
import classes from "./CourseManagementPage.module.css"
import Modal from "../component/CourseManagementComponent/Modal"


export default function CourseManagementPage()
{
    const [year, setYear] = useState(years[years.length - 1])
    const [semester, setSemester] = useState(semesters[0])
    return(
        <div className={`flex-grow-1 ${classes.wrapper}`}>
            <section className="h-100 container py-5 px-xl-5 px-lg-4 px-md-3 px-sm-1">
                <div className="py-4 px-4 border border-secondary-subtle bg-white rounded-4 shadow-sm mb-4">
                    <div className="d-grid gap-0 row-gap-4">
                        <div className="d-flex gap-0 column-gap-3 align-items-center">
                            <svg style={{color:"#ad46ff"}} xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-calendar" viewBox="0 0 16 16">
                                <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"/>
                            </svg>
                            <div className="fw-bold">
                                학기 선택
                            </div>
                        </div>
                        <div className="d-flex flex-column flex-md-row gap-4 align-items-start align-items-md-center">
                            <div className="d-flex align-items-center gap-0 column-gap-2">
                                <div className="fw-bold">년도:</div>
                                <DropDown handleSelect={setYear} defaultSelect={year} list={years} width={"8rem"}></DropDown>
                            </div>
                            <div className="d-flex align-items-center gap-0 column-gap-2">
                                <div className="fw-bold">학기:</div>
                                <DropDown handleSelect={setSemester} defaultSelect={semester} list={semesters} width={"8rem"}></DropDown>
                            </div>
                            <div className="d-flex align-items-center">
                                <span className="fw-bold fs-5" style={{color:"#9810fa"}}>{year}년 {semester}</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="row gy-3 row-cols-1 row-cols-md-3 mb-4">
                    <div className="col">
                        <div className="d-grid gap-0 row-gap-5 py-4 px-4 border border-secondary-subtle bg-white rounded-4 shadow-sm">
                            <div className="d-flex gap-0 column-gap-2 align-items-center">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" style={{color:"#2b7fff"}} className="bi bi-book" viewBox="0 0 16 16">
                                    <path d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783"/>
                                </svg>
                                <div style={{fontSize:"1.1em"}} className="fw-bold">이번 학기 학점</div>
                            </div>
                            <div>
                                <p style={{color:"#2b7fff"}} className="fw-bold fs-4 mb-0">0학점</p>
                                <small className="text-secondary">총 0개 과목</small>                            
                            </div>
                        </div>
                    </div>
                    <div className="col">
                        <div className="d-grid gap-0 row-gap-5 py-4 px-4 border border-secondary-subtle bg-white rounded-4 shadow-sm">
                            <div className="d-flex gap-0 column-gap-2 align-items-center">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" style={{color:"#00c951"}} className="bi bi-mortarboard" viewBox="0 0 16 16">
                                    <path d="M8.211 2.047a.5.5 0 0 0-.422 0l-7.5 3.5a.5.5 0 0 0 .025.917l7.5 3a.5.5 0 0 0 .372 0L14 7.14V13a1 1 0 0 0-1 1v2h3v-2a1 1 0 0 0-1-1V6.739l.686-.275a.5.5 0 0 0 .025-.917zM8 8.46 1.758 5.965 8 3.052l6.242 2.913z"/>
                                    <path d="M4.176 9.032a.5.5 0 0 0-.656.327l-.5 1.7a.5.5 0 0 0 .294.605l4.5 1.8a.5.5 0 0 0 .372 0l4.5-1.8a.5.5 0 0 0 .294-.605l-.5-1.7a.5.5 0 0 0-.656-.327L8 10.466zm-.068 1.873.22-.748 3.496 1.311a.5.5 0 0 0 .352 0l3.496-1.311.22.748L8 12.46z"/>
                                </svg>
                                <div style={{fontSize:"1.1em"}} className="fw-bold">이번 학기 평점</div>
                            </div>
                            <div>
                                <p style={{color:"#00c951"}} className="fw-bold fs-4 mb-0">0.00</p>
                                <small className="text-secondary">4.5만점 기준</small>                            
                            </div>
                        </div>
                    </div>
                    <div className="col">
                        <div className="h-100 d-grid gap-0 row-gap-5 py-4 px-4 border border-secondary-subtle bg-white rounded-4 shadow-sm">
                            <div style={{fontSize:"1.1em"}} className="fw-bold">과목 구성</div>                            
                            <div>
                                                      
                            </div>
                        </div>
                    </div>
                </div>
                <div className="py-4 px-4 border border-secondary-subtle bg-white rounded-4 shadow-sm mb-4">
                    <div className="d-flex align-items-center">
                        <div className="fw-bold">{year}년 {semester} 수강 과목</div>
                        <button className="btn btn-bd-primary ms-auto d-flex align-items-center gap-0 column-gap-3" data-bs-toggle="modal" data-bs-target="#exampleModal">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2"/>
                            </svg>
                            <span>과목 추가</span>
                        </button>
                    </div>
                </div>
                <div className="py-4 px-4 border border-secondary-subtle bg-white rounded-4 shadow-sm mb-4">
                    <center className="py-4">
                        <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" fill="currentColor" className="bi bi-book text-black text-opacity-50 mb-2" viewBox="0 0 16 16">
                            <path d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783"/>
                        </svg>
                        <p className="text-black text-opacity-50 mb-2">{year}년 {semester}에 등록된 과목이 없습니다.</p>
                        <small className="text-black text-opacity-25">과목 추가 버튼을 눌러 수강한 과목을 기록해보세요.</small>
                    </center>
                </div>
                <Modal year={year} semester={semester}></Modal>
            </section>
        </div>
    )
}