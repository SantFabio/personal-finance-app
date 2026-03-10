
import Layout from '../components/Layout';
import SummaryCard from '../components/SummaryCard';
import PotsSummary from '../components/PotsSummary';
import TransactionsList from '../components/TransactionsList';
import BudgetsSummary from '../components/BudgetsSummary';
import RecurringBillsSummary from '../components/RecurringBillsSummary';

const Overview = () => {
  return (
    <Layout>
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-grey-900">Overview</h1>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-6">
        <SummaryCard title="Current Balance" amount={4836.00} variant="dark" />
        <SummaryCard title="Income" amount={3814.25} />
        <SummaryCard title="Expenses" amount={1700.50} />
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-12 gap-6">
        {/* Left Column (Pots & Transactions) */}
        <div className="lg:col-span-7 flex flex-col gap-6">
          <PotsSummary />
          <TransactionsList />
        </div>

        {/* Right Column (Budgets & Recurring Bills) */}
        <div className="lg:col-span-5 flex flex-col gap-6">
          <BudgetsSummary />
          <RecurringBillsSummary />
        </div>
      </div>
    </Layout>
  );
};

export default Overview;
