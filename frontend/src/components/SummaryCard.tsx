

interface SummaryCardProps {
  title: string;
  amount: number;
  variant?: 'dark' | 'light';
}

const SummaryCard: React.FC<SummaryCardProps> = ({ title, amount, variant = 'light' }) => {
  const isDark = variant === 'dark';

  return (
    <div className={`rounded-xl p-6 ${isDark ? 'bg-grey-900 text-white' : 'bg-white text-grey-900'} shadow-sm`}>
      <h3 className={`text-sm mb-2 ${isDark ? 'text-grey-300' : 'text-grey-500'}`}>{title}</h3>
      <p className="text-3xl font-bold">
        ${amount.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}
      </p>
    </div>
  );
};

export default SummaryCard;
