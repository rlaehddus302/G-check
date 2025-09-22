import { useState } from 'react'
import DropDown from './DropDown'
import classes from './Modal.module.css'

export default function Modal({year, semester})
{
    const languages = [
        "한국어",
        "영어",
        "중국어",
        "일본어",
    ]
    const scores = [
        "A+",
        "A",
        "B+",
        "B",
        "C+",
        "C",
        "D+",
        "D",
        "F"
    ]
    const [language, setLanguage] = useState("한국어")
    const [score, setScore] = useState("성적을 선택하세요")
    return(
            <div className={`modal fade ${classes.wrapper}`} id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog modal-dialog-centered">
                    <div className="modal-content">
                        <div className="modal-header">
                            <div>
                                <h2 className="modal-title fs-5 fw-bold" id="exampleModalLabel">{year}년 {semester} 과목 추가</h2>
                                <small className='text-secondary'>수강한 과목 정보를 입력해주세요.</small>
                            </div>
                            <button style={{height:"0", width:"0"}} type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <div className='d-grid gap-4'>
                                <div className='row'>
                                    <div className='col-3 d-flex align-items-center'>
                                        <label for="exampleFormControlInput1" className="form-label fw-bold mb-0">과목 검색</label>
                                    </div>
                                    <div className='col-9'>
                                        <input type="email" className="form-control" id="exampleFormControlInput1" placeholder="과목명을 입력하세요"></input>
                                    </div>
                                </div>
                                <div className='row'>
                                    <div className='col-3 d-flex align-items-center'>
                                        <label className="form-label fw-bold mb-0">강의 언어</label>
                                    </div>
                                    <div className='col-9'>
                                        <DropDown defaultSelect={language} list={languages} width={"6.5rem"} handleSelect={setLanguage} ></DropDown>
                                    </div>
                                </div>
                                <div className='row'>
                                    <div className='col-3 d-flex align-items-center'>
                                        <label className="form-label fw-bold mb-0">성적</label>
                                    </div>
                                    <div className='col-9'>
                                        <DropDown defaultSelect={score} list={scores} handleSelect={setScore} width={score === "성적을 선택하세요" ? "11rem" : "5rem"} ></DropDown>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary fw-bolder" data-bs-dismiss="modal">추가하기</button>
                        </div>
                    </div>
                </div>
            </div>
    )
}