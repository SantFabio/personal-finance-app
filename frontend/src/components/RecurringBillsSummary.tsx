

const RecurringBillsSummary = () => {
  return (
    <div className="bg-white rounded-xl p-8 shadow-sm">
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-xl font-bold text-grey-900">Recurring Bills</h2>
        <button className="text-grey-500 hover:text-grey-900 text-sm flex items-center">
          See Details <span className="ml-2">›</span>
        </button>
      </div>

      <div className="space-y-4">
        <div className="bg-beige-100 rounded-xl px-4 py-5 flex justify-between items-center border-l-4 border-secondary-green">
          <span className="text-grey-500 text-sm">Paid Bills</span>
          <span className="font-bold text-grey-900">$190.00</span>
        </div>

        <div className="bg-beige-100 rounded-xl px-4 py-5 flex justify-between items-center border-l-4 border-secondary-yellow">
          <span className="text-grey-500 text-sm">Total Upcoming</span>
          <span className="font-bold text-grey-900">$194.98</span>
        </div>

        <div className="bg-beige-100 rounded-xl px-4 py-5 flex justify-between items-center border-l-4 border-secondary-cyan">
          <span className="text-grey-500 text-sm">Due Soon</span>
          <span className="font-bold text-grey-900">$59.98</span>
        </div>
      </div>
    </div>
  );
};

export default RecurringBillsSummary;
