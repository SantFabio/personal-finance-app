import { motion } from 'framer-motion';
import { Wallet } from 'lucide-react';

function App() {
  return (
    <div className="min-h-screen flex items-center justify-center flex-col gap-6">
      <motion.div
        initial={{ scale: 0.8, opacity: 0 }}
        animate={{ scale: 1, opacity: 1 }}
        transition={{ duration: 0.5 }}
        className="p-8 bg-white rounded-2xl shadow-xl flex items-center gap-4"
      >
        <Wallet size={48} className="text-blue-600" />
        <h1 className="text-4xl font-bold bg-clip-text text-transparent bg-gradient-to-r from-blue-600 to-cyan-500">
          Personal Finance App
        </h1>
      </motion.div>
      <p className="text-gray-500 max-w-md text-center">
        O projeto frontend React + TypeScript + Tailwind + Framer Motion foi inicializado com sucesso.
      </p>
    </div>
  );
}

export default App;
