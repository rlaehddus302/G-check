
const currentYear = new Date().getFullYear();
export const years = Array.from({ length: currentYear - 2020 + 1 }, (_, i) => 2020 + i);

export const semesters = [
    "1학기",
    "여름학기",
    "2학기",
    "겨울학기",
]

