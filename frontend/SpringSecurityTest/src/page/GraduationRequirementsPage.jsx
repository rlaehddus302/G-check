import { redirect, useLoaderData } from "react-router-dom"
import { loginCheck } from "../util/LoginCheck"
import classes from './GraduationRequirementsPage.module.css'
import CreditCard from "../component/GradRequirPageComponent/CreditCard"
import OtherRequirements from "../component/GradRequirPageComponent/OtherRequirements"
import GeneralEducation from "../component/GradRequirPageComponent/GeneralEducation"
import Major from "../component/GradRequirPageComponent/Major"


export default function GraduationRequirementsPage()
{
    let major = localStorage.getItem("major")
    let admissionYear = localStorage.getItem("admissionYear")
    let data = useLoaderData()
    console.log(data)
    return(
        <div className="flex-grow-1">
            <section style={{paddingLeft:""}} className="h-100 container py-4 px-xl-5 px-lg-4 px-md-3 px-sm-1">
                <div className="d-flex gap-0 column-gap-3 mb-3">
                    <div className="d-flex justify-content-center align-items-center">
                        <div className={`d-flex align-items-center justify-content-center bg-gradient-custom rounded-4 ${classes.gradient}`} style={{width: "3.0rem", height: "3.0rem"}}>
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" className="bi bi-mortarboard text-white" viewBox="0 0 16 16">
                                <path d="M8.211 2.047a.5.5 0 0 0-.422 0l-7.5 3.5a.5.5 0 0 0 .025.917l7.5 3a.5.5 0 0 0 .372 0L14 7.14V13a1 1 0 0 0-1 1v2h3v-2a1 1 0 0 0-1-1V6.739l.686-.275a.5.5 0 0 0 .025-.917zM8 8.46 1.758 5.965 8 3.052l6.242 2.913z"/>
                                <path d="M4.176 9.032a.5.5 0 0 0-.656.327l-.5 1.7a.5.5 0 0 0 .294.605l4.5 1.8a.5.5 0 0 0 .372 0l4.5-1.8a.5.5 0 0 0 .294-.605l-.5-1.7a.5.5 0 0 0-.656-.327L8 10.466zm-.068 1.873.22-.748 3.496 1.311a.5.5 0 0 0 .352 0l3.496-1.311.22.748L8 12.46z"/>
                            </svg>
                        </div>
                    </div>
                    <div>
                        <div className="fw-bolder fs-2">{major} 졸업 요건</div>
                        <div className="text-secondary">{admissionYear}학번 기준 졸업 요건들</div>
                    </div>
                </div>
                <div className="border border-secondary-subtle bg-white rounded-4 shadow-sm mb-3">
                    <div className="py-3 d-grid gap-0 row-gap-4">
                        <div className="px-3">   
                            <div className="fw-bold fs-6">졸업 요건 개요</div>
                            <div style={{fontSize:"0.8em"}} className="text-secondary">{major} 졸업 조건들</div>
                        </div>
                        <div className="row gy-3 gx-5 row-cols-1 row-cols-md-4 px-3">
                            <CreditCard fontColor={"#155dfc"} style={{backgroundColor:"#eff6ff"}} className="text-center rounded-3 py-3" content={"총 이수 학점"} credit={data.totalCredit}></CreditCard>
                            <CreditCard fontColor={"#00a63e"} style={{backgroundColor:"#f0fdf4"}} className="text-center rounded-3 py-3" content={"전공 학점"} credit={data.major.totalCredit}></CreditCard>
                            <CreditCard fontColor={"#9810fa"} style={{backgroundColor:"#faf5ff"}} className="text-center rounded-3 py-3" content={"교양 학점"} credit={data.generalEducation.totalCredit}></CreditCard>
                            <CreditCard fontColor={"#f54a00"} style={{backgroundColor:"#fff7ed"}} className="text-center rounded-3 py-3" content={"자유 선택 학점"} credit={data.totalCredit - data.major.totalCredit - data.generalEducation.totalCredit}></CreditCard>
                        </div>
                    </div>
                </div>
                <GeneralEducation data={data.generalEducation}></GeneralEducation>
                <Major data={data.major}></Major>
                <OtherRequirements></OtherRequirements>
            </section>
        </div>
    )
}

export async function loader({request, param}) {
    let returnValue = await loginCheck()
    if(returnValue)
    {
        let major = localStorage.getItem("major")
        let admissionYear = localStorage.getItem("admissionYear")
        const response = await fetch(`http://localhost:8080/graduation-requirements?department=${major}&year=${admissionYear}`, {
            credentials: 'include'
        });
        return response
    }
    else
    {
        throw redirect("/login")
    }
}
