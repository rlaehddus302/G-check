export default function SummaryField({ label, value }) {
  return (
    <div className="col">
        <div className="d-flex flex-column gap-1">
            <span className="text-secondary small">{label}</span>
            <strong>{value}</strong>
        </div>
    </div>
  );
}
